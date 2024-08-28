import { TestBed } from '@angular/core/testing';

import { DepartmentNotificationService } from './department-notification.service';

describe('DepartmentNotificationService', () => {
  let service: DepartmentNotificationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DepartmentNotificationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
