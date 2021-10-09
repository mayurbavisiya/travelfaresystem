import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { TravelHistoryRoutingModule } from './travel-history-routing.module';
import { TravelHistoryComponent } from './travel-history.component';

@NgModule({
    imports: [
        FormsModule,
        ReactiveFormsModule,
        CommonModule,
        RouterModule,
        TravelHistoryRoutingModule
    ],
    declarations: [
        TravelHistoryComponent
    ]
})

export class TravelHistoryModule { }
