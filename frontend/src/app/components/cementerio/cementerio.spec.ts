import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Cementerio } from './cementerio';

describe('Cementerio', () => {
  let component: Cementerio;
  let fixture: ComponentFixture<Cementerio>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Cementerio]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Cementerio);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
