import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import 'rxjs/add/observable/throw';
import "rxjs/add/operator/catch";

@Injectable()
export class RequestService {

  postRequestURL = "http://localhost:8080/tickit/request";
  postAMQ = "http://localhost:8161/api/message/RequestsToBroker?type=queue";

  constructor(protected httpClient: HttpClient) {

  }

  public postRequest(project: string, request: string, summary: string) {
    const headers = {
      // Authorization: `Basic ${btoa("admin:admin")}`,
      'Content-Type': 'text/plain'
    };

    return this.httpClient.post(`${this.postRequestURL}`, "message enzo",{headers}).catch(this.handleError);
  }

  public postRequestAMQ(request: string, type:string, summary: string)
  {
    const headers = new HttpHeaders({
      // 'Authorization' : `Basic ${btoa("admin:admin")}`,
      'Content-Type': 'application/json'
    });

    let body = {
      request: request,
      type:type,
      summary:summary
    };
    return this.httpClient.post(`${this.postAMQ}`, body,{headers, responseType: 'text'}).catch(this.handleError);
  }


  private handleError(error: Response) {
    console.log(error);
    return Observable.throw(error);
  }
}
