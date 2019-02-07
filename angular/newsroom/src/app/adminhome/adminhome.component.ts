import { Component, OnInit, ViewChild } from '@angular/core';
import * as $ from 'jQuery';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adminhome',
  templateUrl: './adminhome.component.html',
  styleUrls: ['./adminhome.component.css']
})
export class AdminhomeComponent implements OnInit {
  model: any;
  user: any;
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.userService.getAllUser().subscribe(data => {
      this.user = data;
      console.log(this.user);
    });
  }

  search(keys) {
    console.log(keys);
    this.userService.getUserByKey(keys).subscribe(data => {
      this.user = data;
      console.log(this.user);
    });
  }

  activate(userObject) {
    this.userService.toggleUser(userObject).subscribe(data => {
      console.log(data);
    });
    window.location.reload();
  }
  toggleRole(user) {
    console.log(user);
  }
}
