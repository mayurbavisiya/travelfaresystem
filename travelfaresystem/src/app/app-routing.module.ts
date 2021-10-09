import { NgModule } from '@angular/core';
import { Routes, RouterModule} from '@angular/router';

const routes: Routes = [
    {
        path: 'swip-card',
        loadChildren: () => import('./component/home/home.module').then(mod => mod.HomeModule)
    },
    {
        path: 'travel-history',
        loadChildren: () => import('./component/travel-history/travel-history.module').then(mod => mod.TravelHistoryModule)
    },
    {
        path: '**' ,
        redirectTo: 'swip-card', 
        pathMatch: 'full'
    }
];



@NgModule({
    imports: [RouterModule.forRoot(routes, {
    useHash: false,
    relativeLinkResolution: 'legacy'
})],
    exports: [RouterModule]
})

export class AppRoutingModule { }
