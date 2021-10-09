import { Injectable, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { BehaviorSubject } from 'rxjs';

// Services
import { ConfigService } from './config.service';
import { ApiService } from './api.service';

@Injectable()
export class GeneralService {
    constructor(
        public _configService: ConfigService,
        public _apiService: ApiService
    ) { }

    // Get Stations
    getStationsApi(): Observable<any> | any {
        const apiUrl = 'lookup/stations';
        return this._apiService.request('get', apiUrl, {}, {}, false);
    }

    // Modes
    getModesApi(): Observable<any> | any {
        const apiUrl = 'lookup/modes';
        return this._apiService.request('get', apiUrl, {}, {}, true);
    }

    // Swipe In
    saveSwipeInApi(data: any): Observable<any> | any {
        const apiUrl = 'swipe/in';
        return this._apiService.request('post', apiUrl, data, {}, true);
    }

    // Swipe OUT
    saveSwipeOutApi(data: any): Observable<any> | any {
        const apiUrl = 'swipe/out';
        return this._apiService.request('post', apiUrl, data, {}, true);
    }

    // Travel history
    getTravelHistoryApi(id: any): Observable<any> | any {
        const apiUrl = 'traveHistory/'+ id;
        return this._apiService.request('get', apiUrl, {}, {}, true);
    }
}



