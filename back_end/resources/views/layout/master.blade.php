<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
    <meta content="Coderthemes" name="author" />
    <!-- App favicon -->
    <link rel="shortcut icon" href="">

    @include('layout.css')
</head>

<body class="" data-layout-config="{&quot;leftSideBarTheme&quot;:&quot;dark&quot;,&quot;layoutBoxed&quot;:false, &quot;leftSidebarCondensed&quot;:false, &quot;leftSidebarScrollable&quot;:false,&quot;darkMode&quot;:false, &quot;showRightSidebarOnStart&quot;: true}" data-leftbar-theme="dark">

@include('layout.sidebar')

<div class="">

    @include('layout.header')

    <div class="content-page">
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">

                        @if (session()->has('success'))
                            <div class="alert alert-success mt-2">
                                {{ session()->get('success') }}
                            </div>
                        @endif

                        @if (session()->has('error'))
                            <div class="alert alert-danger mt-2">
                                {{ session()->get('error') }}
                            </div>
                        @endif

                        @yield('content')
                    </div>
                </div>
            </div>
        </div>
    </div>

    @include('layout.footer')

</div>

@include('layout.script')

</body>
</html>
