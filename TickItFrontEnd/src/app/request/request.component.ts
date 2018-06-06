import { Component, OnInit } from '@angular/core';
import {RequestService} from "../request.service";

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent implements OnInit {

  request;
  summary;
  request_type;

  constructor(private requestService: RequestService) { }

  ngOnInit() {
  }

  postRequestAMQ(){
    this.requestService.postRequestAMQ(this.request, this.request_type, this.summary ).subscribe(res => {
        alert("Ticket succesfully created");
      },
      (error) => console.log("error", error)
    );
  }

}
