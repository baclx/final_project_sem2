<?php

namespace App\Http\Controllers;

use App\Models\Category;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\View;

class CategoryController extends Controller
{

    public function index(Request $request)
    {
        $search = $request->get('q');
        $data = Category::query()
            ->withCount('product')
            ->where('name', 'like', '%'.$search.'%')
            ->paginate(4);
        $data->appends(['q' => $search]);

        return view('client.category.index', [
            'categories' => $data,
            'search' => $search,
        ]);
    }


    public function create()
    {
        return view('client.category.create');
    }


    public function store(Request $request)
    {
//        dd($request);
        Category::create($request->all());

        return redirect()->route('categories.index');
    }


    public function show($id)
    {
        //
    }


    public function edit(Category $category)
    {

        return view('client.category.edit', [
            'each' => $category
        ]);
    }


    public function update(Request $request, Category $category)
    {
        $category->update(
            $request->all(),
        );

        return redirect()->route('categories.index');
    }


    public function destroy(Category $category)
    {
        $category->delete();

        return redirect()->route('categories.index');
    }
}
