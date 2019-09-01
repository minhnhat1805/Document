import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs'; 

import { User } from '../user';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl:string = 'http://localhost:8080/api/users';
  private headers = new HttpHeaders({'Content-Type':'application/json'});
  private options = {headers: this.headers}
  constructor(private _http:HttpClient) { }

  getUsers(): Observable<any>{
    return this._http.get(`${this.baseUrl}`,this.options);
  }

  getUser(id:String):Observable<Object>{
    return this._http.get(`${this.baseUrl}+${id}`,this.options);

  }

  deleteUser(id:String): Observable<any>{
    return this._http.delete(`${this.baseUrl}+${id}`,this.options);
  }
   updateUser(id:String):Observable<Object>{
    return this._http.put(`${this.baseUrl}+${id}`,this.options);

  }
  createUser(user:User):Observable<Object>{
    return this._http.post(`${this.baseUrl}`,JSON.stringify(user),this.options);

  }

  errorHandler(error:Response){
      return Observable.throw(error|| "SERVER ERROR");
  }
}