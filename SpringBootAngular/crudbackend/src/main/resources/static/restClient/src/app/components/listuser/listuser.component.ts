import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { UserService } from 'src/app/shared_service/user.service';
import { Observable, from } from 'rxjs';


@Component({
  selector: 'app-listuser',
  templateUrl: './listuser.component.html',
  styleUrls: ['./listuser.component.scss']
})
export class ListuserComponent implements OnInit {

    users: Observable<User[]>;

  constructor(private _userService: UserService) { }


  reloadData() {
    this.users = this._userService.getUsers();
  }
  ngOnInit() {
    this.reloadData();
    
  }

  deleteUser(id: String) {
    this._userService.deleteUser(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

}
