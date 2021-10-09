import { Component, OnInit, AfterViewInit, ElementRef, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GeneralService } from 'src/app/services/general.service';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

declare let jQuery: any;

@Component({
    selector: 'app-travel-history',
    templateUrl: './travel-history.component.html',
    styleUrls: ['./travel-history.component.scss']
})

export class TravelHistoryComponent  implements OnInit, AfterViewInit, OnDestroy {
    public nativeEl: any;
    public accountForm!: FormGroup;
    public tripsList: any[] = [];
    public responseDescError: string = '';
    private getTravelHistoryServiceUnsubscribe: any = null;
    
    constructor(
        private el: ElementRef,
        public fb: FormBuilder,
        public route: ActivatedRoute,
        public router: Router,
        private generalService: GeneralService
    ) {
        this.nativeEl = this.el.nativeElement;
    }

    ngOnInit() {
        this.accountForm = this.fb.group({
            cardId: ['', Validators.required]
        });
     }

    ngAfterViewInit() {
        let sidebarToggle = jQuery('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        jQuery(sidebarToggle ).on('click', (event: any) => {
       
            event.preventDefault();
            jQuery('#sidebar-wrapper').toggle('sb-sidenav-toggled');
            // localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }
    }

    get f() {
        return this.accountForm.controls;
    }

    onSubmitAccountForm() {
        if (this.accountForm.invalid) {
            return;
        }

        this.getTravelHistoryServiceUnsubscribe = this.generalService.getTravelHistoryApi(this.accountForm.value.cardId).subscribe((res: any) => {
            try {
                let data = res || {};
                if (data.responseCode === '1') {
                    console.log(res);
                    this.tripsList = data.trips;
                    // setTimeout( ()=> {
                        jQuery('.TravelHistoryModal').modal('show');
                    // }, 1000);
                } else {
                    console.log('Error');
                    this.responseDescError = 'No Data Found.';
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

    ngOnDestroy() {
        if(this.getTravelHistoryServiceUnsubscribe) {
            this.getTravelHistoryServiceUnsubscribe.unsubscribe();
        }
    }
}
