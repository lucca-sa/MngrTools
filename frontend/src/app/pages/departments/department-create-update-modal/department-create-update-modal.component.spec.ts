import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentCreateUpdateModalComponent } from './department-create-update-modal.component';

describe('DepartmentCreateUpdateModalComponent', () => {
  let component: DepartmentCreateUpdateModalComponent;
  let fixture: ComponentFixture<DepartmentCreateUpdateModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DepartmentCreateUpdateModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepartmentCreateUpdateModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
