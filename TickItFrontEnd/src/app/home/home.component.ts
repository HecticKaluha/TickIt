import {Component, OnInit} from '@angular/core';
import {TicketWsService} from "../ticket-ws.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private finishedTickets;

  constructor(ticketWs: TicketWsService) {

    this.finishedTickets = [];
    /*ticketWs.messages.subscribe(msg => {
      console.log("response from websocket: " + msg);
      this.finishedTickets.push(
        {
          type: msg.type,
          request: msg.request,
          summary: msg.summary,
          finished: msg.finished,
        }
      );
    });*/
  }

  ngOnInit() {
  }

}
