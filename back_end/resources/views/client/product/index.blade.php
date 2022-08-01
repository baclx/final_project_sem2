@extends('layout.master')
@section('content')
    <h1>Product</h1>

    <a class="btn btn-primary mb-4 mt-2" href="{{ route('products.create') }}">
        Add
    </a>

    <form class="float-right d-flex" action="">
        <label class="control-label mr-2 d-flex align-items-center">Search: </label>
        <input class="border border-none form-control mb-2" name="q" value="{{ $search }}">
    </form>

    @if (session()->has('success_product'))
        <div class="alert alert-success mt-2">
            {{ session()->get('success_product') }}
        </div>
    @endif

    <table class="table table-dark table-striped">
        <tbody>
        <tr>
            <th>id</th>
            <th>Name</th>
            <th>Category_id</th>
            <th>Show</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>

        @foreach($products as $each)
            <tr>
                <td>{{ $each->id }}</td>
                <td>{{ $each->name }}</td>
                <td>{{ $each->category_id }}</td>
                <td>
                    <a class="btn btn-info" href="{{ route('products.show', $each) }}">
                        Show
                    </a>
                </td>
                <td>
                    <a class="btn btn-primary" href="{{ route('products.edit', $each) }}">
                        Edit
                    </a>
                </td>
                <td>
                    <form action="{{ route('products.destroy', $each) }}"
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
            {{ $products->links() }}
        </ul>
    </nav>
@endsection
