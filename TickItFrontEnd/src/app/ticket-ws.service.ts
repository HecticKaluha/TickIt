import {Injectable} from '@angular/core';
import {Subject} from "rxjs/Subject";
import {WebsocketService} from "./websocket.service";
import Stomp from 'stompjs';
import {StompService} from "@stomp/ng2-stompjs";

const WS_URL = '"ws://localhost:61614/stomp';
//console.log(Stomp);
//let client = Stomp.client("ws://localhost:61614/stomp", "v11.stomp");
//let stomp_subscription = this._stompService.subscribe('/topic/ng-demo-sub');

//console.log(Stomp);

export interface Message {
  type: string,
  request: string,
  summary: string,
  finished: boolean,
}


@Injectable()
export class TicketWsService {

  public allTickets: any = [];
  public javaTickets: any = [];
  public angularTickets: any = [];
  public csharpTickets: any = [];

  constructor(wsService: WebsocketService, private client: StompService) {

    let stomp_subscription = this.client.subscribe('/queue/BrokerToClient');

    stomp_subscription.map((message: any) => {
      return message.body;
    }).subscribe((msg_body: string) => {
      let parsed = JSON.parse(JSON.parse(msg_body));
      switch (parsed.type) {
        case 'Angular':
          this.angularTickets.push(parsed);
          console.log("added to Angular");
          break;
        case 'Java':
          this.javaTickets.push(parsed);
          console.log("added to Java");
          break;
        case 'C#':
          this.csharpTickets.push(parsed);
          console.log("added to C#");
      }
      this.allTickets.push(parsed);
    });
  }
}
