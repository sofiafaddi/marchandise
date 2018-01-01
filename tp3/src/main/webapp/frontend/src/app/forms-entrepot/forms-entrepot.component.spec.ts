import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormsEntrepotComponent } from './forms-entrepot.component';

describe('FormsEntrepotComponent', () => {
  let component: FormsEntrepotComponent;
  let fixture: ComponentFixture<FormsEntrepotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormsEntrepotComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormsEntrepotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
