import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCreateUpdateModalComponent } from './user-create-update-modal.component';

describe('UserCreateUpdateModalComponent', () => {
  let component: UserCreateUpdateModalComponent;
  let fixture: ComponentFixture<UserCreateUpdateModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserCreateUpdateModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserCreateUpdateModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
