@extends('layout.master');
@section('content')
    <h1>User</h1>
    <table class="table table-dark table-striped">
        <tbody>
        <tr>
            <th>id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Gender</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        @foreach($users as $user)
            <tr>
                <td>{{ $user->id }}</td>
                <td>{{ $user->name }}</td>
                <td>{{ $user->email }}</td>
                <td>{{ $user->phone }}</td>
                <td>{{ $user->gender_name }}</td>
{{--                <td>--}}
{{--                    <a class="btn btn-primary" href="{{ route('users.edit', $user) }}">--}}
{{--                        Edit--}}
{{--                    </a>--}}
{{--                </td>--}}
{{--                <td>--}}
{{--                    <form action="{{ route('users.destroy', $user) }}"--}}
{{--                          method="post"--}}
{{--                    >--}}
{{--                        @csrf--}}
{{--                        @method('DELETE')--}}
{{--                        <button class="btn btn-danger">Delete</button>--}}
{{--                    </form>--}}
{{--                </td>--}}
            </tr>
        @endforeach
        </tbody>
    </table>
    <br>
    <nav class="Page navigation example">
        <ul class="pagination pagination-rounded mb-0">
            {{ $users->links() }}
        </ul>
    </nav>
@endsection
