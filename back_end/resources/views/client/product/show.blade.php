@extends('layout.master')
@section('content')
    <h1 class="mb-4">Show Product</h1>

    <table class="table table-dark table-striped">
        <tbody>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Category Name</th>
            <th>Description</th>
            <th>Quantity</th>
            <th>Created_at</th>
        </tr>
        <tr>
            <td>{{ $product->id }}</td>
            <td>{{ $product->name }}</td>
            <td>{{ $product->category->name }}</td>
            <td>{{ $product->desc }}</td>
            <td>{{ $product->qty }}</td>
            <td>{{ $product->all_created_at }}</td>
        </tr>
        </tbody>
    </table>
    <br>
    <a href="{{ route('products.index') }}">
        Back
    </a>
@endsection
