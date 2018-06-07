import {Component, OnInit} from '@angular/core';
import {TicketWsService} from "../ticket-ws.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(public ticketWs: TicketWsService) {

  }

  ngOnInit() {
  }

}
