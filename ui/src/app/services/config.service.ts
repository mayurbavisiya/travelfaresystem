import { Injectable, EventEmitter } from '@angular/core';

@Injectable()
export class ConfigService {
    constructor( ) { }

    getApiEndPoint() {
        return 'http://localhost:8090/travelfare/api/';
       // return 'http://7577-94-202-69-70.ngrok.io/travelfare/api/';
    }
}
