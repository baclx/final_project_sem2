<?php


use App\Http\Controllers\UserController;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('layout.master');
});

Route::group([
    'as' => 'users.',
    'prefix' => 'users'
], function () {
    Route::get('/', [UserController::class, 'index'])->name('index');
    Route::get('/edit', [UserController::class, 'edit'])->name('edit');
});
