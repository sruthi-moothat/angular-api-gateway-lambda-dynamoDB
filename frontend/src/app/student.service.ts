import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from './student';


@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private baseUrl1 = 'https://tlz199pvec.execute-api.us-east-1.amazonaws.com/Dev/students';
  private baseUrl2 = 'https://tlz199pvec.execute-api.us-east-1.amazonaws.com/Dev/students';

  

  constructor(private http: HttpClient) { }

  createStudent(student: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl2}`, student);
  }
    getStudentsList(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.baseUrl1}`);
  }
}