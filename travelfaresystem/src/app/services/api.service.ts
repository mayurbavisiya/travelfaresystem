import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { ConfigService } from './config.service';

declare let navigator: any;
declare let window: any;

@Injectable()

export class ApiService {
    loaderEmitter: EventEmitter<any> = new EventEmitter();

    constructor(
        private http: HttpClient,
        private _configService: ConfigService,
        ) {
    }

    request(method: any, url: any, data: any, param: any, loder: any = '') {

        let endPoint = this._configService.getApiEndPoint();
        let apiURL = endPoint + url;
        let headerConfig = { 'Content-Type': 'application/json' } as any;
        // if (method === 'get') {
        //     headerConfig = { 'Content-Type': 'application/json' } as any;
        // }
        // if (method === 'post') {
        //     headerConfig = { 'Content-Type': 'application/x-www-form-urlencoded' } as any;
        // }

        const headers = new HttpHeaders(headerConfig);
        let body = JSON.stringify(data);
        const params = this.objToSearchParams(param);
        let options = { params, withCredentials: false, headers };

        let request;
        if (method === 'post') {
            request = this.http.post(apiURL, body, options).map((res) => {
                return this.extractData(res);
            }).pipe(catchError((error: any) => {
                return throwError(this.handleError(error));
            }));
        } else if (method === 'get') {
            request = this.http.get(apiURL, options).map((res) => {
                return this.extractData(res);
            }).pipe(catchError((error: any) => {
                return throwError(this.handleError(error));
            }));
        } else if (method === 'put') {
            request = this.http.put(apiURL, body, options).map((res) => {
                return this.extractData(res);
            }).pipe(catchError((error: any) => {
                return throwError(this.handleError(error));
            }));
        }
        return request;
    }

    private objToSearchParams(obj: any) {
        let params = new HttpParams();
        for (const key in obj) {
            if (obj.hasOwnProperty(key)) {
                const val = obj[key];
                if (val !== null && val !== undefined) {
                    params = params.append(key, val.toString());
                }
            }
        }
        return params;
    }

    private extractData(res: any) {

        if (res.status < 200 || res.status >= 300) {
            throw new Error('Bad response status: ' + res.status);
        }
        return res || {};
    }

    private handleError(error: any) {
        console.log(error);
        this.loaderEmitter.emit(false);
        let errMsg = error.message || 'Server error';

        if (error.status === 404 || error.status === 401 || error.status === 400 || error.status === 403 || error.status === 409) {
            errMsg = error.error;
        }
        return errMsg;
    }
}
