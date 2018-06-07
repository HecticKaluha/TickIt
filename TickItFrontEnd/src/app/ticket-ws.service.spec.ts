import { TestBed, inject } from '@angular/core/testing';

import { TicketWsService } from './ticket-ws.service';

describe('TicketWsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TicketWsService]
    });
  });

  it('should be created', inject([TicketWsService], (service: TicketWsService) => {
    expect(service).toBeTruthy();
  }));
});
