import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarchandisesComponent } from './marchandises.component';

describe('MarchandisesComponent', () => {
  let component: MarchandisesComponent;
  let fixture: ComponentFixture<MarchandisesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarchandisesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarchandisesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
