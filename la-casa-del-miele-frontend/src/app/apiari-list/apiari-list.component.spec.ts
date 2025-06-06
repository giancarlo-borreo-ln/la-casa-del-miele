import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApiariListComponent } from './apiari-list.component';

describe('ApiariListComponent', () => {
  let component: ApiariListComponent;
  let fixture: ComponentFixture<ApiariListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ApiariListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApiariListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
