import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormReceptionnerComponent } from './form-receptionner.component';

describe('FormReceptionnerComponent', () => {
  let component: FormReceptionnerComponent;
  let fixture: ComponentFixture<FormReceptionnerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormReceptionnerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormReceptionnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
