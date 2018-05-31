import { Component, OnInit } from '@angular/core';
import {RequestService} from "../request.service";

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent implements OnInit {

  constructor(private requestService: RequestService) { }

  ngOnInit() {
  }

  postRequestAMQ(){
    this.requestService.postRequestAMQ("", "", "" ).subscribe(res => {
        console.log("aangekomen");
      },
      (error) => console.log("error", error)
    );
  }

}
