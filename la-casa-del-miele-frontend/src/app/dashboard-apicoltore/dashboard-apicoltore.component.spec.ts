import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardApicoltoreComponent } from './dashboard-apicoltore.component';

describe('DashboardApicoltoreComponent', () => {
  let component: DashboardApicoltoreComponent;
  let fixture: ComponentFixture<DashboardApicoltoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashboardApicoltoreComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardApicoltoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
