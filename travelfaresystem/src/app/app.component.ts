import { Component, OnInit, EventEmitter, AfterViewInit, OnDestroy, HostListener } from '@angular/core';
import { ActivatedRoute, Router, NavigationEnd, NavigationStart, RoutesRecognized } from '@angular/router';

declare let window: any;
declare let jQuery: any;

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, AfterViewInit, OnDestroy {

    constructor(
        public route: ActivatedRoute,
        public router: Router
    ) { }

    ngOnInit() { }

    ngAfterViewInit() { }

   
    ngOnDestroy() { }
}
