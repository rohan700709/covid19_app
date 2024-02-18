import { TestBed } from '@angular/core/testing';

import { CountryDataService } from './country-data.service';

describe('CountryDataService', () => {
  let service: CountryDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CountryDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
