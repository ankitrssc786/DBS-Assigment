import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '@/_models';
import { Request } from '@/_models';

@Injectable({ providedIn: 'root' })
export class UserService {

    private baseUrl = 'http://localhost:8080/api/v1/request';

    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<Request[]>(`${this.baseUrl}/`);
    }

    getByStatus() {
        return this.http.get<Request[]>(`${this.baseUrl}/${status}`);
    }

    create(userRequest: Request[]) {
        return this.http.post(`${this.baseUrl}/`, userRequest);
    }

    update(userRequest: Request[]) {
        return this.http.put(`${this.baseUrl}/`, userRequest);
    }

    delete(accountNumber: number) {
        return this.http.delete(`${this.baseUrl}/${accountNumber}`);
    }
}