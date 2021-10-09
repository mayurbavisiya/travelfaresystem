$(document).ready(function() {
	loadStocks();
});

function loadStocks() {
	var url = 'api/trade/getOpenTrades';
	$
			.ajax({
				type : "GET",
				url : url,
				data : null,
				cache : false,
				processData : false,
				contentType : false,
				async : true,
				success : function(response) {
					if ($.fn.DataTable.isDataTable('#stockListTable')) {
						$('#stockListTable').DataTable().destroy();
					}

					var dataSet = response.stocks;

					$('#stockListTable')
							.DataTable(
									{
										"bProcessing" : true,
										"font-size" : "13px",

										"data" : dataSet,
										"columns" : [
												{
													"data" : "id",
													"title" : "Id"
												},
												{
													"data" : "stockName",
													"title" : "Stock Name"
												},
												{
													"data" : "buyPrice",
													"title" : "Buying Price"
												},
												{
													"data" : "quantity",
													"title" : "Quantity"
												},
												{
													"data" : "totalPrice",
													"title" : "Total Price"
												},
												{
													"data" : "isSellStock",
													"title" : "Sell Stock"
												},
												{
													"data" : "exitPrice",
													"title" : "Exit Price"
												},
												{
													"data" : "exitComment",
													"title" : "Exit Comment"
												},
												{
													"title" : "Profit/Loss",
													"render" : function(data, type, full, row) {
														if ( $.isNumeric(full.profit)) {
															if(parseInt(full.profit) < 0){
																return '<span style="color:red">'+full.profit+'</span>';
															}
															return '<span style="color:green">'+full.profit+'</span>';
														} else {
															return full.profit;
														}
													}
												},
												{
													"data" : "createdDate",
													"title" : "Created Date"
												},
												{
													"title" : "Updated Date",
													"render" : function(data,
															type, full, row) {
														if (full.tradeFinish == 0) {
															return '-';
														} else {
															return full.updatedTime;
														}
													}
												},
												{
													"title" : "Action",
													"render" : function(data,
															type, full, row) {
														if (full.tradeFinish == 1) {
															return 'Exited';
														} else {
															return '<button type="button"  id="button_'
																	+ full.id
																	+ '" onclick="exitTradePopupOpen('
																	+ full.id
																	+ ');"  class="btn btn-info">Exit</button>';
														}
													}
												} ],
										"columnDefs" : [ {
											"className" : "dt-center",
											"targets" : "_all"
										} ],
										 "order": [[ 0, "desc" ]]
									});
				},
				error : function(e) {
					BootstrapDialog.show({
						title : 'Error Occured! Please contact admin',
						message : '',
						buttons : [ {
							label : 'Okay',
							action : function(dialogItself) {
								dialogItself.close();
							}
						} ]
					});
				}
			});
}

function resetModal() {
	$('#stockIdHiddenText').val('');
	$('#stockNametxt').val('');
	$('#buyingPriceTxt').val('');
	$('#exitPriceTxt').val('');
	$('#quantityTxt').val('');
	$('#isSellStock').attr('checked',false); //Standards compliant

	
	
}

function addNewStock() {
	$('#stockModal').modal('show');
	resetModal();
	$('#modalButton').html('Save');
}

function exitTradePopupOpen(tradeId) {
	$('#exitTradePopup').modal('show');
	$('#stockIdHiddenText').val(tradeId);
}

function exitTrade() {
	if ($('#exitPriceTxt').val() == '') {		
		$('#exiTrademessageSpan').css('color', 'red');
		$('#exiTrademessageSpan').html('Exit Price is required').show('slow');
		$('#exiTrademessageSpan').delay(3000).fadeOut();
		$('#exitPriceTxt').focus();
		return false;
	}
	var stockId = $('#stockIdHiddenText').val().trim();
	
	
	var formdata = '{"exitComment":"' + $('#exitCommentTxt').val().trim() + '"}';
	
	
	$.ajax({
		url : 'api/trade/' + parseInt(stockId) + '/'
				+ $('#exitPriceTxt').val().trim(),
		type : 'PUT',
		data : formdata,
		processData : false,
		async : true,
		timeout : 300000,
		contentType : 'application/json',

		error : function(jqXHR, textStatus, errorThrown) {

			BootstrapDialog.show({
				title : 'Error Occured! Please contact admin',
				message : '',
				buttons : [ {
					label : 'Okay',
					action : function(dialogItself) {
						dialogItself.close();
					}
				} ]
			});
		},
		success : function(response) {
			var msg = '';
			if (response.responseCode == 1) {
				msg = 'Stock Exited successfully';
				BootstrapDialog.show({
					title : msg,
					message : '',
					buttons : [ {
						label : 'Okay',
						action : function(dialogItself) {
							dialogItself.close();
						}
					} ]
				});
				$('#exitTradePopup').modal('hide');
				loadStocks();
			} else {
				msg = respData.responseDesc;
				BootstrapDialog.show({
					title : msg,
					message : '',
					buttons : [ {
						label : 'Okay',
						action : function(dialogItself) {
							dialogItself.close();
						}
					} ]
				});
			}
		}
	});

}

function saveOrUpdateStock() {
	if ($('#modalButton').html() == 'Save') {
		saveStock();
	} else {
		updateStock();
	}
}

function validateForm() {
	if ($('#stockNametxt').val() == '') {
		bootstrapDialogue('Stock Name is required', 'red');
		$('#stockNametxt').focus();
		return false;
	}
	if ($('#buyingPriceTxt').val() == '') {
		bootstrapDialogue('Buying Price is required', 'red');
		$('#buyingPriceTxt').focus();
		return false;
	}

	return true;
}

function saveStock() {
	if (!validateForm())
		return;
	
	var isChecked = 0;
	if ($('#isSellStock').is(':checked')) {
		isChecked = 1;
	}
	var formdata = '{"stockName":"' + $('#stockNametxt').val().trim()
			+ '","buyPrice":"' + $('#buyingPriceTxt').val().trim()
			+ '","isSellStock":"' + parseInt(isChecked)
			+ '","quantity":"' + parseInt($('#quantityTxt').val().trim()) +'"}';

	var saveUrl = 'api/trade';
	$.ajax({
		url : saveUrl,
		type : 'POST',
		data : formdata,
		processData : false,
		async : true,
		timeout : 300000,
		contentType : 'application/json',

		error : function(jqXHR, textStatus, errorThrown) {
			BootstrapDialog.show({
				title : 'Error Occured! Please contact admin',
				message : '',
				buttons : [ {
					label : 'Okay',
					action : function(dialogItself) {
						dialogItself.close();
					}
				} ]
			});
		},
		success : function(respData) {
			var msg = '';
			if (respData.responseCode == 1) {
				msg = 'Stock saved successfully';
				BootstrapDialog.show({
					title : msg,
					message : '',
					buttons : [ {
						label : 'Okay',
						action : function(dialogItself) {
							dialogItself.close();
						}
					} ]
				});
				$('#stockModal').modal('hide');
				loadStocks();
			} else {
				msg = respData.responseDesc;
				BootstrapDialog.show({
					title : msg,
					message : '',
					buttons : [ {
						label : 'Okay',
						action : function(dialogItself) {
							dialogItself.close();
						}
					} ]
				});
			}
		}
	});

}

function updateStock() {
	if (!validateForm())
		return;

	var formdata = '{"stockName":"' + $('#stockNametxt').val().trim()
			+ '","stockDesc":"' + $('#stockDesctxt').val().trim()
			+ '","currentPrice":"' + $('#stockCurrentPriceTxt').val().trim()
			+ '"}';

	var updateUrl = 'api/stocks/'
			+ parseInt($('#stockIdHiddenText').val().trim());
	$.ajax({
		url : updateUrl,
		type : 'PUT',
		data : formdata,
		processData : false,
		async : true,
		timeout : 300000,
		contentType : 'application/json',

		error : function(jqXHR, textStatus, errorThrown) {
			BootstrapDialog.show({
				title : 'Error Occured! Please contact admin',
				message : '',
				buttons : [ {
					label : 'Okay',
					action : function(dialogItself) {
						dialogItself.close();
					}
				} ]
			});
		},
		success : function(respData) {
			var msg = '';
			if (respData.responseCode == 1) {
				msg = 'Stock updated successfully';
				BootstrapDialog.show({
					title : msg,
					message : '',
					buttons : [ {
						label : 'Okay',
						action : function(dialogItself) {
							dialogItself.close();
						}
					} ]
				});
				$('#stockModal').modal('hide');
				loadStocks();
			} else {
				msg = respData.responseDesc;
				BootstrapDialog.show({
					title : msg,
					message : '',
					buttons : [ {
						label : 'Okay',
						action : function(dialogItself) {
							dialogItself.close();
						}
					} ]
				});
			}
		}
	});

}

function deleteStock(stockId) {
	$.ajax({
		url : 'api/stocks/' + parseInt(stockId),
		type : 'DELETE',
		data : null,
		processData : false,
		async : true,
		timeout : 300000,
		contentType : 'application/json',

		error : function(jqXHR, textStatus, errorThrown) {

			BootstrapDialog.show({
				title : 'Error Occured! Please contact admin',
				message : '',
				buttons : [ {
					label : 'Okay',
					action : function(dialogItself) {
						dialogItself.close();
					}
				} ]
			});
		},
		success : function(response) {
			var respData = response.responseCode;
			if (respData == 1) {
				BootstrapDialog.show({
					title : 'Stock Deleted Successfully',
					message : '',
					buttons : [ {
						label : 'Okay',
						action : function(dialogItself) {
							dialogItself.close();
						}
					} ]
				});
				loadStocks();
			}
		}
	});

}

function bootstrapDialogue(msg, color) {
	// loader('hide');
	$('#messageSpan').css('color', color);
	$('#messageSpan').html(msg).show('slow');
	$('#messageSpan').delay(3000).fadeOut();
}
