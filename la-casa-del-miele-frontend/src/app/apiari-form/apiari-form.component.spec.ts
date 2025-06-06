import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApiariFormComponent } from './apiari-form.component';

describe('ApiariFormComponent', () => {
  let component: ApiariFormComponent;
  let fixture: ComponentFixture<ApiariFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ApiariFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApiariFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
