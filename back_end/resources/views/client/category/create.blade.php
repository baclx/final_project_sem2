@extends('layout.master')
@section('content')
    <form action="{{ route('categories.store') }}" method="post">
        @csrf
        <h1>
            Create Category
        </h1>
        <div class="form-group row mt-4">
            <label class="col-sm-1 col-form-label">Name: </label>
            <div class="col-sm-11">
                <input type="text" class="form-control" name="name" placeholder="Enter your name category">
            </div>
        </div>
        <div class="text-center">
            <button type="submit" class="mt-4 btn btn-primary">Submit</button>
        </div>
    </form>
    <br>
    <a href="{{ route('categories.index') }}">
        Back
    </a>
@endsection
