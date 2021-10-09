import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TravelHistoryComponent } from './travel-history.component';

@NgModule({
    imports: [
        RouterModule.forChild([
            { path: '', component: TravelHistoryComponent }
        ])
    ]
})

export class TravelHistoryRoutingModule { }
