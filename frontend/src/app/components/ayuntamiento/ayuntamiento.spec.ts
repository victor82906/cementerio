import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Ayuntamiento } from './ayuntamiento';

describe('Ayuntamiento', () => {
  let component: Ayuntamiento;
  let fixture: ComponentFixture<Ayuntamiento>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Ayuntamiento]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Ayuntamiento);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
