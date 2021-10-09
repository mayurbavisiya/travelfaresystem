import { Component, OnInit, Inject, ElementRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

declare let jQuery: any;

@Component({
    selector: 'swip-inout-modal',
    templateUrl: './swip-inout-modal.component.html',
    styleUrls: ['./swip-inout-modal.component.scss']
})

export class SwipInoutModalComponent implements OnInit {
    public swipData: any;

    constructor(
        public route: ActivatedRoute,
        public router: Router,
        public dialogRef: MatDialogRef<SwipInoutModalComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
        public dialog: MatDialog
    ) {
        this.swipData = data;
        console.log('swipData', this.swipData);
    }

    ngOnInit() {}


    ngAfterViewInit() { }

    modalClose() {
        this.dialogRef.close();
    }

    ngOnDestroy() { }
}
