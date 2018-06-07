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

  public messages: Subject<Message>;

  constructor(wsService: WebsocketService , private client: StompService) {
    let stomp_subscription = this.client.subscribe('/queue/BrokerToClient');

    stomp_subscription.map((message: any) => {
      return message.body;
    }).subscribe((msg_body: string) => {
      console.log("non parsed", msg_body);
      let parsed = JSON.parse(msg_body);
      console.log(`Received: ${parsed}`);
    });

    /*client.connect("", "",
      function () {
        client.subscribe("/queue/BrokerToClient",
          function (message) {
            alert(message);
          },
          {priority: 9}
        );
      }
    );*/
  }


    /*8this.messages = <Subject<any>>wsService
      .connect(WS_URL)
      .map((response: MessageEvent): Message => {
        console.log(response.data);

        let data = JSON.parse(response.data);
        return {
          type: data.type,
          request: data.request,
          summary: data.summary,
          finished: data.finished
        }
      });
  }*/

}
