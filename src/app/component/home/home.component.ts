import { Component, OnInit, AfterViewInit, ElementRef, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { GeneralService } from 'src/app/services/general.service';
import { SwipInoutModalComponent } from 'src/app/modal/swip-inout-modal/swip-inout-modal.component';

declare let jQuery: any;

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})

export class HomeComponent  implements OnInit, AfterViewInit, OnDestroy {
    public nativeEl: any;
    public accountForm!: FormGroup;
    public stationsList: any[] = [];
    public modesList: any[] = [];
    public swipeInResponse: any = {};
    public swipeOutResponse: any = {};
    public responseDescError: any;

    private getStationsServiceUnsubscribe: any = null;
    private getModesServiceUnsubscribe: any = null;
    private saveSwipeInServiceUnsubscribe: any = null;
    private saveSwipeOutServiceUnsubscribe: any = null;
    
    constructor(
        private el: ElementRef,
        public route: ActivatedRoute,
        public router: Router,
        public fb: FormBuilder,
        public dialog: MatDialog,
        private generalService: GeneralService
    ) {
        this.nativeEl = this.el.nativeElement;
    }

    ngOnInit() {
        this.accountForm = this.fb.group({
            cardId: ['', Validators.required],
            entryStation: ['', Validators.required],
            mode: ['', Validators.required]
        });
        this.getStationsList();
        this.getModesList();
     }

    ngAfterViewInit() {
        let sidebarToggle = jQuery('#sidebarToggle');
        if (sidebarToggle) {
            jQuery(sidebarToggle ).on('click', (event: any) => {
        
                event.preventDefault();
                jQuery('#sidebar-wrapper').toggle('sb-sidenav-toggled');
            });
        }
    }

    getStationsList() {
        this.getStationsServiceUnsubscribe = this.generalService.getStationsApi().subscribe((res: any) => {
            try {
                let data = res || {};
                if (data.responseCode === '1') {
                    console.log(res);
                    this.stationsList = res.stations;
                } else {
                    console.log('Error')
                }
            } catch (e) {
                console.log(e);
                console.log('exception caught in getStationsList');
            }
        }, (error: any) => {
            console.log(error)
        });
    };

    getModesList() {
        this.getModesServiceUnsubscribe = this.generalService.getModesApi().subscribe((res: any) => {
            try {
                let data = res || {};
                if (data.responseCode === '1') {
                    console.log(res);
                    this.modesList = res.modes;
                } else {
                    console.log('Error')
                }
            } catch (e) {
                console.log(e);
                console.log('exception caught in getModesList');
            }
        }, (error: any) => {
            console.log(error)
        });
    };

    get f() {
        return this.accountForm.controls;
    }

    onSubmitAccountForm(type: any) {
        if (this.accountForm.invalid) {
            return;
        }

        if(type === 'swipin') {
            let params = {
                cardId: +this.accountForm.value.cardId,
                entryStation: +this.accountForm.value.entryStation,
                mode: +this.accountForm.value.mode
            } as any;
            this.saveSwipInData(params);
        } 

        if(type === 'swipout') {
            let params = {
                cardId: this.accountForm.value.cardId,
                exitStation: this.accountForm.value.entryStation
            } as any;
            this.saveSwipOutData(params);
        } 
    }

    saveSwipInData(params: any) {
        this.saveSwipeInServiceUnsubscribe = this.generalService.saveSwipeInApi(params).subscribe((res: any) => {
            try {
                let data = res || {};
                if (data.responseCode === '1') {
                    console.log(res);
                    this.swipeInResponse = data.swipeInResponse;
                    jQuery('.SwipInModal').modal('show');
                } else {
                    console.log('Error');
                    this.responseDescError = data.responseDesc;
                    jQuery('.errorModal').modal('show');
                }
            } catch (e) {
                console.log(e);
                console.log('exception caught in saveSwipInData');
            }
        }, (error: any) => {
            console.log(error)
        });
    }

    saveSwipOutData(params: any) {
        this.saveSwipeOutServiceUnsubscribe = this.generalService.saveSwipeOutApi(params).subscribe((res: any) => {
            try {
                let data = res || {};
                if (data.responseCode === '1') {
                    console.log(res);
                    this.swipeOutResponse = data.swipeOutResponse;
                    jQuery('.SwipOutModal').modal('show');
                } else {
                    console.log('Error');
                    this.responseDescError = data.responseDesc;
                    jQuery('.errorModal').modal('show');
                }
            } catch (e) {
                console.log(e);
                console.log('exception caught in saveSwipOutData');
            }
        }, (error: any) => {
            console.log(error)
        }); 
    }

    modalClose() {
        jQuery('.SwipInModal').modal('hide');
    }

    ngOnDestroy() {
        if(this.getStationsServiceUnsubscribe) {
            this.getStationsServiceUnsubscribe.unsubscribe();
        }
        if(this.getModesServiceUnsubscribe) {
            this.getModesServiceUnsubscribe.unsubscribe();
        }
        if(this.saveSwipeInServiceUnsubscribe) {
            this.saveSwipeInServiceUnsubscribe.unsubscribe();
        }
        if(this.saveSwipeOutServiceUnsubscribe) {
            this.saveSwipeOutServiceUnsubscribe.unsubscribe();
        }
    }
}
