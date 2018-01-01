import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormApprovisionnementComponent } from './form-approvisionnement.component';

describe('FormApprovisionnementComponent', () => {
  let component: FormApprovisionnementComponent;
  let fixture: ComponentFixture<FormApprovisionnementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormApprovisionnementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormApprovisionnementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
