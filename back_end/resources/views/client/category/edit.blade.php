@extends('layout.master')
@section('content')
    <form action="{{ route('categories.update', $each) }}" method="post">
        @csrf
        @method('PUT')
        <h1>
            Edit Category
        </h1>
        <div class="form-group row mt-4">
            <label class="col-1 col-form-label">Name: </label>
            <div class="col-11">
                <input type="text" class="form-control" name="name" placeholder="Enter your name category" value="{{ $each->name }}">
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
