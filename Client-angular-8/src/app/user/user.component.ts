import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Request, User } from "./../_models";
import { FormBuilder, FormGroup } from '@angular/forms';
import { first } from 'rxjs/operators';

import { UserService, AuthenticationService } from '@/_services';

@Component({ templateUrl: 'user.component.html' })
export class UserComponent implements OnInit {
    userForm: FormGroup;
    loading = false;
    submitted = false;
    editField: string;
    userRequestList: Request[];
    user: User;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private authenticationService: AuthenticationService,
        private userService: UserService,
    ) {
        // redirect to home if already logged in
        if (this.authenticationService.currentUserValue) {
            this.router.navigate(['/']);
        }
    }

    ngOnInit() {
        this.reloadData();
    }

    reloadData() {
        this.userService.getAll()
            .subscribe(data => {
                this.userRequestList = data;
            });
    }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.userForm.invalid) {
            return;
        }

        this.loading = true;
        this.userService.create(this.userForm.value)
            .pipe(first())
            .subscribe(
                data => {
                    this.router.navigate(['/user']);
                },
                error => {
                    this.loading = false;
                });
    }

    update() {

        this.loading = true;
        this.userService.update(this.userRequestList)
            .pipe(first())
            .subscribe(
                data => {
                    this.router.navigate(['/user']);
                },
                error => {
                    this.loading = false;
                });
    }

    deleteList(id: number) {

        this.loading = true;
        this.userService.delete(id)
            .subscribe(
                data => {
                    this.router.navigate(['/user']);
                },
                error => {
                    this.loading = false;
                });
    }

    updateList(id: number, property: string, event: any) {
        const editField = event.target.textContent;
        this.userRequestList[id][property] = editField;
        this.update();
    }

    remove(id: any) {
        this.userRequestList.push(this.userRequestList[id]);
        this.userRequestList.splice(id, 1);
        this.deleteList(id);
    }

    add() {
        if (this.userRequestList.length > 0) {
            const person = this.userRequestList[0];
            this.userRequestList.push(person);
            this.userRequestList.splice(0, 1);
        }
    }

    changeValue(id: number, property: string, event: any) {
        this.editField = event.target.textContent;
    }
}
