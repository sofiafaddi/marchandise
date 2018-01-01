import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormMarchandiseComponent } from './form-marchandise.component';

describe('FormMarchandiseComponent', () => {
  let component: FormMarchandiseComponent;
  let fixture: ComponentFixture<FormMarchandiseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormMarchandiseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormMarchandiseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
