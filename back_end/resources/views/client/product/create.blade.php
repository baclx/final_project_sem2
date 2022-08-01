@extends('layout.master')
@section('content')
    <form action="{{ route('products.store') }}" method="post">
        @csrf
        <h1>
            Create Product
        </h1>
        <div class="form-group row mt-4">
            <label class="col-sm-1 col-form-label">Name: </label>
            <div class="col-sm-11">
                <input type="text" class="form-control" name="name" placeholder="Enter your category">
            </div>
        </div>
        <div class="form-group row mt-4">
            <label class="col-sm-1 col-form-label">Description: </label>
            <div class="col-sm-11">
                <input type="text" class="form-control" name="desc" placeholder="Enter your description">
            </div>
        </div>
        <div class="form-group row mt-4">
            <label class="col-sm-1 col-form-label">Qty: </label>
            <div class="col-sm-11">
                <input type="text" class="form-control" name="qty" placeholder="Enter your qty">
            </div>
        </div>
        <div class="form-group row mt-4">
            <label class="col-sm-1 col-form-label">Category: </label>
            <div class="col-sm-11">
                <select class="form-select form-control" name="category_id">
                    @foreach($categories as $category)
                        <option value="{{ $category->id }}">
                            {{ $category->name }}
                        </option>
                    @endforeach
                </select>
            </div>
        </div>
        <div class="text-center">
            <button type="submit" class="mt-4 btn btn-primary">Submit</button>
        </div>
    </form>
    <br>
    <a href="{{ route('products.index') }}">
        Back
    </a>
@endsection
