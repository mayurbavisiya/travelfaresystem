import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { SwipInoutModalComponent } from 'src/app/modal/swip-inout-modal/swip-inout-modal.component';

@NgModule({
    imports: [
        FormsModule,
        ReactiveFormsModule,
        CommonModule,
        RouterModule,
        HomeRoutingModule
    ],
    declarations: [
        HomeComponent,
        // SwipInoutModalComponent
    ]
})

export class HomeModule { }
