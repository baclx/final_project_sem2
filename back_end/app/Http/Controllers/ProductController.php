<?php

namespace App\Http\Controllers;

use App\Models\Category;
use App\Models\Product;
use Illuminate\Http\Request;

class ProductController extends Controller
{

    public function index(Request $request)
    {
        $search = $request->get('q');
        $data = Product::query()
            ->with('category')
            ->where('name', 'like', '%'.$search.'%')
            ->paginate(4);
        $data->appends(['q' => $search]);

        return view('client.product.index', [
            'products' => $data,
            'search' => $search,
        ]);
    }


    public function create()
    {
        $data = Category::all();

        return view('client.product.create', [
            'categories' => $data,
        ]);
    }


    public function store(Request $request)
    {
        Product::create($request->all());

        return redirect()->route('products.index');
    }


    public function show(Product $product)
    {
        $categories = Category::all();

        return view('client.product.show', [
            'product' => $product,
            'categories' => $categories,
        ]);
    }


    public function edit(Product $product)
    {
        $data = Category::all();


        return view('client.product.edit', [
            'products' => $product,
            'categories' => $data,
        ]);
    }


    public function update(Request $request, Product $product)
    {
        $product->update(
            $request->all(),
        );

        return redirect()->route('products.index');
    }


    public function destroy(Product $product)
    {
        $product->delete();

        return redirect()->route('products.index');
    }
}
