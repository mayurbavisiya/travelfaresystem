$(document).ready(function() {
	loadBalance();
});

function loadBalance() {
	var url = 'api/trade/getBalance';
	$.ajax({
		type : "GET",
		url : url,
		data : null,
		cache : false,
		processData : false,
		contentType : false,
		async : true,
		success : function(response) {
			if ($.fn.DataTable.isDataTable('#balanceDataTable')) {
				$('#balanceDataTable').DataTable().destroy();
			}

			var dataSet = response.balanceList;
			
			$('#availablebalanceSpanid').text('Available Balance is : '+response.availableBalance);

			$('#balanceDataTable').DataTable({
				"bProcessing" : true,
				"font-size" : "13px",

				"data" : dataSet,
				"columns" : [ {
					"data" : "id",
					"title" : "Id"
				}, {
					"data" : "amount",
					"title" : "Balance"
				}, {
					"data" : "creditdate",
					"title" : "Credit Date"
				} ],
				"columnDefs" : [ {
					"className" : "dt-center",
					"targets" : "_all"
				} ],
				"order" : [ [ 0, "desc" ] ]
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

function addBalanceModal() {
	$('#balanceModal').modal('show');
	resetModal();
}
function resetModal() {
	$('#balanceTxt').val('');
}

function saveBalance() {
	if ($('#balanceTxt').val() == '') {
		bootstrapDialogue('Balance is required', 'red');
		$('#balanceTxt').focus();
		return false;
	}
	
	

	var formdata = '{"amount":"' + $('#balanceTxt').val().trim() + '"}';
	var saveUrl = 'api/trade/saveBalance';
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
				msg = 'Balance updated successfully';
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
				$('#balanceModal').modal('hide');
				loadBalance();
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

function saveStock() {
	var formdata = '{"amount":"' + $('#balanceTxt').val().trim() + '"}';
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
