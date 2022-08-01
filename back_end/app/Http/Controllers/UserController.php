<?php

namespace App\Http\Controllers;

use App\Models\User;

class UserController extends Controller
{
    public function apiUsers() {
        $users = User::all();
        return $users;
    }

    public function index() {
        $data = User::query()->paginate(8);

        return view('admin.user.index', [
            'users' => $data,
        ]);
    }

    public function edit() {

    }

    public function delete() {

    }
}
