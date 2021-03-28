import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectmultiComponent } from './selectmulti.component';

describe('SelectmultiComponent', () => {
  let component: SelectmultiComponent;
  let fixture: ComponentFixture<SelectmultiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectmultiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectmultiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
