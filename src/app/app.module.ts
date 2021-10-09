import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { MatDialogModule } from '@angular/material/dialog';
import { SharedModule } from './services/shared.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SwipInoutModalComponent } from './modal/swip-inout-modal/swip-inout-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    SwipInoutModalComponent
  ],

  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatDialogModule,
    SharedModule.forRoot()
  ],
  entryComponents: [],
  providers: [ ],
  bootstrap: [AppComponent]
})
export class AppModule { }
