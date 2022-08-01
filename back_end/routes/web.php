<?php

use App\Http\Controllers\CategoryController;
use App\Http\Controllers\ProductController;
use Illuminate\Support\Facades\Route;

//Route::get("/category", [CategoryController::class, 'index'])->name('categories.index');
//Route::get("/category", [CategoryController::class, 'create']);
//Route::post("/category", [CategoryController::class, 'store'])->name('categories.store');


Route::resource('categories', CategoryController::class);
Route::resource('products', ProductController::class);
