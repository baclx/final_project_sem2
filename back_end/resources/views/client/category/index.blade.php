@extends('layout.master')
@section('content')
    <h1>Category</h1>

    <a class="btn btn-primary mb-4 mt-2" href="{{ route('categories.create') }}">
        Add
    </a>

    <form class="float-right d-flex" action="">
        <label class="control-label mr-2 d-flex align-items-center">Search: </label>
        <input class="border border-none form-control mb-2" name="q" value="{{ $search }}">
    </form>

    <table class="table table-dark table-striped">
        <tbody>
        <tr>
            <th>id</th>
            <th>Name</th>
            <th>Count</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>

        @foreach($categories as $each)
            <tr>
                <td>{{ $each->id }}</td>
                <td>{{ $each->name }}</td>
                <td>{{ $each->product_count }}</td>
                <td>
                    <a class="btn btn-primary" href="{{ route('categories.edit', $each) }}">
                        Edit
                    </a>
                </td>
                <td>
                    <form action="{{ route('categories.destroy', $each) }}"
                          method="post"
                    >
                        @csrf
                        @method('DELETE')
                        <button class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        @endforeach
        </tbody>
    </table>
    <br>
    <nav class="Page navigation example">
        <ul class="pagination pagination-rounded mb-0">
            {{ $categories->links() }}
        </ul>
    </nav>
@endsection
